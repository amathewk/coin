// Copyright (C) 2018 Anil Mathew.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package controllers

import javax.inject._
import org.mongodb.scala.Document
import play.api._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import repo.TransactionRepo

import scala.concurrent.Future


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, transactionRepo: TransactionRepo) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def transactions(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    import scala.concurrent.ExecutionContext.Implicits.global

    transactionRepo.transactions().map { docs: Seq[Document] =>
      Ok(
        docs.foldLeft("") { (s, d) =>
          s + d.toString + "\n"
        }
      )
    }
//    Future.successful(Ok("transactions"))
  }

  def create: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    import scala.concurrent.ExecutionContext.Implicits.global

    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    val saveResult = jsonBody.map { json =>

      val name = (json \ "name").as[String]
      val x = (json \ "x").as[String]
      val y = (json \ "y").as[String]
      transactionRepo.save(
        name, x, y
      )
    }.getOrElse(Future.failed(new RuntimeException("Failed")))

    //TODO: verify of both non-happy paths are handled correctly, i.e. no option and future not completed successfully.
    saveResult map (_ => Created)

//    Future.successful(Ok(""))
//      .map(result => Created)

  }

}

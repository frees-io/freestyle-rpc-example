/*
 * Copyright 2017 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package routeguide

import cats.implicits._
import freestyle._
import freestyle.implicits._
import freestyle.async.implicits._
import freestyle.rpc.client.implicits._
import freestyle.asyncCatsEffect.implicits._
import routeguide.handlers.RouteGuideClientHandler
import routeguide.protocols.RouteGuideService
import monix.eval.Task
import monix.eval.instances.CatsAsyncInstances._
import routeguide.runtime._

object clientT {

  trait Implicits extends RouteGuide with ClientConf {

    implicit val routeGuideServiceClient: RouteGuideService.Client[Task] =
      RouteGuideService.client[Task](channel)

    implicit val routeGuideClientHandler: RouteGuideClientHandler[Task] =
      new RouteGuideClientHandler[Task]
  }

  object implicits extends Implicits

}

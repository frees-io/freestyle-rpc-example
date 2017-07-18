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

package freestyle.rpc
package demo
package protocolgen

import freestyle.rpc.demo.protocolgen.protocols._
import io.grpc._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import freestyle.async.implicits._

object ClientApp {

  def main(args: Array[String]): Unit = {

    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext(true).build

    val client: GreetingService.Client[Future] = GreetingService.client[Future](channel)

    val result = Await.result(client.sayHello(MessageRequest("hi", None)), Duration.Inf)
    println(result)

    (): Unit
  }
}

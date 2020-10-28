package com.Account.assignment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;

public class NextVerticle extends AbstractVerticle {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new AccountGeneratorVerticle());
    vertx.deployVerticle(new MainVerticle());
    vertx.deployVerticle(new NextVerticle());
  }

  @Override
  public void start() throws Exception {
    super.start();

    DeliveryOptions options = new DeliveryOptions();
    options.addHeader("action", "accountInfo");

    JsonObject message = new JsonObject().put("id", "1");

    vertx.eventBus().send("account.service", message, options, res -> {
      if (res.succeeded()){
        System.out.println(res.result().body().toString());
      } else {
        System.out.println("this Response Failed");
      }
    });
  }

  @Override
  public void stop() throws Exception {
    super.stop();
  }
}

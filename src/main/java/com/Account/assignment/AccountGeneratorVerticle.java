package com.Account.assignment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Random;

public class AccountGeneratorVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(AccountGeneratorVerticle.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new AccountGeneratorVerticle());
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.setPeriodic(new Random().nextInt(5000)+1000, doThis -> {
      int id = new Random().nextInt(5000)+1000;
      logger.info("Account Generated");
      vertx
        .eventBus()
        .publish("Account.generate", new JsonObject().put("message", "new Account Generated with id : "+id));
    });
  }

  @Override
  public void stop() throws Exception {
    super.stop();
  }
}

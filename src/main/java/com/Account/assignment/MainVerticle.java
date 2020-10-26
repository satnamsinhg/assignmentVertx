package com.Account.assignment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ServiceBinder;

public class MainVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new AccountGeneratorVerticle());
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start() throws Exception {
    super.start();
    logger.info("Starting Main Verticle");

    AccountService service = AccountService.create(vertx);

    new ServiceBinder(vertx)
      .setAddress("account.service")
      .register(AccountService.class, service);

    vertx.setPeriodic(3000, doThis -> {
      service.accountInfo( res -> {
        if (res.succeeded()){
          System.out.println(res.result().toString());
        }
      });
    });
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    logger.info("Stopping Main Verticle");
  }
}

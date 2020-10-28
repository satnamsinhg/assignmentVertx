package com.Account.assignment;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ServiceBinder;
import io.vertx.serviceproxy.ServiceProxyBuilder;

public class MainVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(MainVerticle.class);

  @Override
  public void start() throws Exception {

    super.start();
    logger.info("Starting Main Verticle");
    AccountService service = AccountService.create(vertx);

    ServiceBinder serviceBinder = new ServiceBinder(vertx);
    serviceBinder
      .setAddress("account.service")
      .registerLocal(AccountService.class, service);

    ServiceProxyBuilder builder = new ServiceProxyBuilder(vertx).setAddress("account.service");
    AccountService accountService = builder.build(AccountService.class);
    AccountService proxy = AccountService.createProxy(vertx, "account.service");



//    vertx.setPeriodic(5000, doThis -> {
//      proxy.accountInfo( 2, res -> {
//        if (res.succeeded()){
//          System.out.println("Response Successful");
//          System.out.println(res.result().toString());
//        } else {
//          System.out.println("Response Failure");
//        }
//      });
//    });
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    logger.info("Stopping Main Verticle");
  }
}

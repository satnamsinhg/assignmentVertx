package com.Account.assignment;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class AccountServiceImpl implements AccountService {
  private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

  Vertx vertx;

  public AccountServiceImpl(Vertx vertx) {
    this.vertx = vertx;
  }

  @Override
  public void accountInfo(int id, Handler<AsyncResult<String>> handler) {
    EventBus request = vertx.eventBus().send("Account.findById", id+"", reply -> {
      if (reply.succeeded()) {
        String s = reply.result().body().toString();
        handler.handle(Future.succeededFuture(s));
      }
      else if (reply.failed()){
        logger.info("Reply Failed");
      }
    });
  }

}

package com.Account.assignment;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class AccountServiceImpl implements AccountService {

  private JsonObject lastAccountInfo;

  public AccountServiceImpl(Vertx vertx) {
    vertx.eventBus().consumer("Account.generate", receive -> {
      lastAccountInfo = (JsonObject) receive.body();
      System.out.println(receive.body());
    });
  }

  @Override
  public void accountInfo(Handler<AsyncResult<JsonObject>> handler) {
    handler.handle(Future.succeededFuture(this.lastAccountInfo));
  }
}

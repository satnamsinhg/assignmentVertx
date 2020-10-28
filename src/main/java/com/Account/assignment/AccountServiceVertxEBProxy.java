package com.Account.assignment;

import io.netty.handler.codec.http.FullHttpRequest;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class AccountServiceVertxEBProxy implements AccountService{

  private Vertx vertx;

  public AccountServiceVertxEBProxy(Vertx vertx, String address) {
    this.vertx = vertx;
  }

  @Override
  public void accountInfo(int id, Handler<AsyncResult<String>> handler) {
    AccountService.create(vertx).accountInfo(1, handler);
  }
}

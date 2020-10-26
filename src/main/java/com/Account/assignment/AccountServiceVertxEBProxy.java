package com.Account.assignment;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class AccountServiceVertxEBProxy implements AccountService{

  private int numberOfAccounts;

  public AccountServiceVertxEBProxy(Vertx vertx, String address) {
  }

  @Override
  public void accountInfo(Handler<AsyncResult<JsonObject>> handler) {
  }
}

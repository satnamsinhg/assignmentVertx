package com.Account.assignment;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface AccountService {

  static AccountService create(Vertx vertx){
    return new AccountServiceImpl(vertx);
  }

  static AccountService createProxy(Vertx vertx, String address){
    return new AccountServiceVertxEBProxy(vertx, address);
  }

  void accountInfo(int id, Handler<AsyncResult<String>> handler);
}

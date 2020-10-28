package com.Account.assignment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class AccountGeneratorVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(AccountGeneratorVerticle.class);

  private static List<User> userList = new ArrayList<>();


  @Override
  public void start() throws Exception {
    this.userList.add(new User(1, "Satnam", "New York"));
    this.userList.add(new User(2, "Ajmer", "Punjab"));
    this.userList.add(new User(3, "Prabh", "Punjab"));
    this.userList.add(new User(4, "Kamak", "Punjab"));
    this.vertx
      .eventBus()
      .consumer("Account.findById", message -> {
        int id = Integer.parseInt(message.body().toString());
        Optional<User> first = this.userList
          .stream()
          .filter(user -> user.getId() == id)
          .findFirst();

        JsonObject jsonObject = JsonObject.mapFrom(first.get());
        message.reply(Json.encodePrettily(jsonObject));
      });
  }
  @Override
  public void stop() throws Exception {
    super.stop();
  }
}

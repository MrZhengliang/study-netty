package com.study.netty.attribute;

import com.study.netty.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {


    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}

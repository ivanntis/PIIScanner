package org.websec;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;

public class ExpressionLoggerExtension implements BurpExtension {

    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("PII Scanner Extension");
        Logging logging = api.logging();
        api.proxy().registerResponseHandler(new PiiHttpResponseHandler(logging));
    }
}
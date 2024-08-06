package org.websec;

import burp.api.montoya.proxy.http.InterceptedResponse;
import burp.api.montoya.proxy.http.ProxyResponseHandler;
import burp.api.montoya.proxy.http.ProxyResponseReceivedAction;
import burp.api.montoya.proxy.http.ProxyResponseToBeSentAction;
import burp.api.montoya.logging.Logging;


import static burp.api.montoya.core.HighlightColor.BLUE;

class PiiHttpResponseHandler implements ProxyResponseHandler {
    private final Logging logger;

    public PiiHttpResponseHandler(Logging logger) {
        this.logger = logger;
    }

    @Override
    public ProxyResponseReceivedAction handleResponseReceived(InterceptedResponse interceptedResponse) {
        //Highlight all responses that have username in them
        if (interceptedResponse.bodyToString().contains("CPF")) {
            this.logger.logToOutput("Tiene CPF 1");
            this.logger.raiseInfoEvent("Tiene CPF 2");
            return ProxyResponseReceivedAction.continueWith(interceptedResponse, interceptedResponse.annotations().withHighlightColor(BLUE));
        }
        return ProxyResponseReceivedAction.continueWith(interceptedResponse);
    }

    @Override
    public ProxyResponseToBeSentAction handleResponseToBeSent(InterceptedResponse interceptedResponse) {
        return ProxyResponseToBeSentAction.continueWith(interceptedResponse);
    }
}
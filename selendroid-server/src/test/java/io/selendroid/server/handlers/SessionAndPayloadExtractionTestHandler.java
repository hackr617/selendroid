/*
 * Copyright 2012 selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.server.handlers;

import org.json.JSONException;
import org.json.JSONObject;
import io.selendroid.server.RequestHandler;
import io.selendroid.server.Response;
import io.selendroid.server.SelendroidResponse;
import org.webbitserver.HttpRequest;

public class SessionAndPayloadExtractionTestHandler extends RequestHandler {

  public SessionAndPayloadExtractionTestHandler(HttpRequest request, String mappedUri) {
    super(request, mappedUri);
  }

  public Response handle() throws JSONException{
    JSONObject payload = getPayload();
    String method = payload.getString("using");
    String selector = payload.getString("value");
    return new SelendroidResponse(null, "sessionId#" + getSessionId() + " using#" + method + " value#"
        + selector);
  }
}

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
package io.selendroid.server.handler;

import org.json.JSONException;
import io.selendroid.exceptions.SelendroidException;
import io.selendroid.exceptions.StaleElementReferenceException;
import io.selendroid.server.RequestHandler;
import io.selendroid.server.Response;
import io.selendroid.server.SelendroidResponse;
import io.selendroid.server.model.AndroidElement;
import io.selendroid.util.SelendroidLogger;
import org.webbitserver.HttpRequest;

public class SubmitForm extends RequestHandler {

  public SubmitForm(HttpRequest request,String mappedUri) {
    super(request,mappedUri);
  }

  @Override
  public Response handle() throws JSONException {
    SelendroidLogger.log("Submit element command");
    String id = getElementId();
    AndroidElement element = getElementFromCache(id);
    if (element == null) {
      return new SelendroidResponse(getSessionId(), 10, new SelendroidException("Element with id '"
          + id + "' was not found."));
    }
    String sessionId = getSessionId();
    try {
      element.submit();
    } catch (StaleElementReferenceException se) {
      return new SelendroidResponse(getSessionId(), 10, se);
    } catch (Exception e) {
      SelendroidLogger.log("error while submitting the element: ", e);
      return new SelendroidResponse(sessionId, 13, e);
    }
    return new SelendroidResponse(sessionId, "");
  }

}

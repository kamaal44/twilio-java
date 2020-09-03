/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service.user;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.List;

public class UserBindingReader extends Reader<UserBinding> {
    private final String pathServiceSid;
    private final String pathUserSid;
    private List<UserBinding.BindingType> bindingType;

    /**
     * Construct a new UserBindingReader.
     *
     * @param pathServiceSid The SID of the Service to read the resource from
     * @param pathUserSid The SID of the User with the User Bindings to read
     */
    public UserBindingReader(final String pathServiceSid,
                             final String pathUserSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathUserSid = pathUserSid;
    }

    /**
     * The push technology used by the User Binding resources to read. Can be:
     * `apn`, `gcm`, or `fcm`.  See [push notification
     * configuration](https://www.twilio.com/docs/chat/push-notification-configuration) for more info..
     *
     * @param bindingType The push technology used by the User Binding resources to
     *                    read
     * @return this
     */
    public UserBindingReader setBindingType(final List<UserBinding.BindingType> bindingType) {
        this.bindingType = bindingType;
        return this;
    }

    /**
     * The push technology used by the User Binding resources to read. Can be:
     * `apn`, `gcm`, or `fcm`.  See [push notification
     * configuration](https://www.twilio.com/docs/chat/push-notification-configuration) for more info..
     *
     * @param bindingType The push technology used by the User Binding resources to
     *                    read
     * @return this
     */
    public UserBindingReader setBindingType(final UserBinding.BindingType bindingType) {
        return setBindingType(Promoter.listOfOne(bindingType));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return UserBinding ResourceSet
     */
    @Override
    public ResourceSet<UserBinding> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return UserBinding ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<UserBinding> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.CHAT.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Users/" + this.pathUserSid + "/Bindings"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return UserBinding ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<UserBinding> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<UserBinding> nextPage(final Page<UserBinding> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.CHAT.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<UserBinding> previousPage(final Page<UserBinding> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.CHAT.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of UserBinding Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<UserBinding> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("UserBinding read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "bindings",
            response.getContent(),
            UserBinding.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (bindingType != null) {
            for (UserBinding.BindingType prop : bindingType) {
                request.addQueryParam("BindingType", prop.toString());
            }
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}

/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v2.service;

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

public class BindingReader extends Reader<Binding> {
    private final String pathServiceSid;
    private List<Binding.BindingType> bindingType;
    private List<String> identity;

    /**
     * Construct a new BindingReader.
     *
     * @param pathServiceSid The SID of the Service to read the resources from
     */
    public BindingReader(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * The push technology used by the Binding resources to read.  Can be: `apn`,
     * `gcm`, or `fcm`.  See [push notification
     * configuration](https://www.twilio.com/docs/chat/push-notification-configuration) for more info..
     *
     * @param bindingType The push technology used by the Binding resources to read
     * @return this
     */
    public BindingReader setBindingType(final List<Binding.BindingType> bindingType) {
        this.bindingType = bindingType;
        return this;
    }

    /**
     * The push technology used by the Binding resources to read.  Can be: `apn`,
     * `gcm`, or `fcm`.  See [push notification
     * configuration](https://www.twilio.com/docs/chat/push-notification-configuration) for more info..
     *
     * @param bindingType The push technology used by the Binding resources to read
     * @return this
     */
    public BindingReader setBindingType(final Binding.BindingType bindingType) {
        return setBindingType(Promoter.listOfOne(bindingType));
    }

    /**
     * The [User](https://www.twilio.com/docs/chat/rest/user-resource)'s `identity`
     * value of the resources to read. See [access
     * tokens](https://www.twilio.com/docs/chat/create-tokens) for more details..
     *
     * @param identity The `identity` value of the resources to read
     * @return this
     */
    public BindingReader setIdentity(final List<String> identity) {
        this.identity = identity;
        return this;
    }

    /**
     * The [User](https://www.twilio.com/docs/chat/rest/user-resource)'s `identity`
     * value of the resources to read. See [access
     * tokens](https://www.twilio.com/docs/chat/create-tokens) for more details..
     *
     * @param identity The `identity` value of the resources to read
     * @return this
     */
    public BindingReader setIdentity(final String identity) {
        return setIdentity(Promoter.listOfOne(identity));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Binding ResourceSet
     */
    @Override
    public ResourceSet<Binding> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Binding ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Binding> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.IPMESSAGING.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Bindings"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Binding ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Binding> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Binding> nextPage(final Page<Binding> page,
                                  final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.IPMESSAGING.toString())
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
    public Page<Binding> previousPage(final Page<Binding> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.IPMESSAGING.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Binding Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Binding> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Binding read failed: Unable to connect to server");
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
            Binding.class,
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
            for (Binding.BindingType prop : bindingType) {
                request.addQueryParam("BindingType", prop.toString());
            }
        }

        if (identity != null) {
            for (String prop : identity) {
                request.addQueryParam("Identity", prop);
            }
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}

/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.call;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class FeedbackFetcher extends Fetcher<Feedback> {
    private String pathAccountSid;
    private final String pathCallSid;

    /**
     * Construct a new FeedbackFetcher.
     *
     * @param pathCallSid The call sid that uniquely identifies the call
     */
    public FeedbackFetcher(final String pathCallSid) {
        this.pathCallSid = pathCallSid;
    }

    /**
     * Construct a new FeedbackFetcher.
     *
     * @param pathAccountSid The unique sid that identifies this account
     * @param pathCallSid The call sid that uniquely identifies the call
     */
    public FeedbackFetcher(final String pathAccountSid,
                           final String pathCallSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathCallSid = pathCallSid;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched Feedback
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Feedback fetch(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Calls/" + this.pathCallSid + "/Feedback.json"
        );

        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Feedback fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Feedback.fromJson(response.getStream(), client.getObjectMapper());
    }
}

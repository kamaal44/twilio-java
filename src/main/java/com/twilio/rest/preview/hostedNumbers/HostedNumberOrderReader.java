/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.hostedNumbers;

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

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class HostedNumberOrderReader extends Reader<HostedNumberOrder> {
    private HostedNumberOrder.Status status;
    private com.twilio.type.PhoneNumber phoneNumber;
    private String incomingPhoneNumberSid;
    private String friendlyName;
    private String uniqueName;

    /**
     * The Status of this HostedNumberOrder. One of `received`,
     * `pending-verification`, `verified`, `pending-loa`, `carrier-processing`,
     * `testing`, `completed`, `failed`, or `action-required`..
     *
     * @param status The Status of this HostedNumberOrder.
     * @return this
     */
    public HostedNumberOrderReader setStatus(final HostedNumberOrder.Status status) {
        this.status = status;
        return this;
    }

    /**
     * An E164 formatted phone number hosted by this HostedNumberOrder..
     *
     * @param phoneNumber An E164 formatted phone number.
     * @return this
     */
    public HostedNumberOrderReader setPhoneNumber(final com.twilio.type.PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * An E164 formatted phone number hosted by this HostedNumberOrder..
     *
     * @param phoneNumber An E164 formatted phone number.
     * @return this
     */
    public HostedNumberOrderReader setPhoneNumber(final String phoneNumber) {
        return setPhoneNumber(Promoter.phoneNumberFromString(phoneNumber));
    }

    /**
     * A 34 character string that uniquely identifies the IncomingPhoneNumber
     * resource created by this HostedNumberOrder..
     *
     * @param incomingPhoneNumberSid IncomingPhoneNumber sid.
     * @return this
     */
    public HostedNumberOrderReader setIncomingPhoneNumberSid(final String incomingPhoneNumberSid) {
        this.incomingPhoneNumberSid = incomingPhoneNumberSid;
        return this;
    }

    /**
     * A human readable description of this resource, up to 64 characters..
     *
     * @param friendlyName A human readable description of this resource.
     * @return this
     */
    public HostedNumberOrderReader setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Provides a unique and addressable name to be assigned to this
     * HostedNumberOrder, assigned by the developer, to be optionally used in
     * addition to SID..
     *
     * @param uniqueName A unique, developer assigned name of this
     *                   HostedNumberOrder.
     * @return this
     */
    public HostedNumberOrderReader setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return HostedNumberOrder ResourceSet
     */
    @Override
    public ResourceSet<HostedNumberOrder> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return HostedNumberOrder ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<HostedNumberOrder> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            "/HostedNumbers/HostedNumberOrders"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return HostedNumberOrder ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<HostedNumberOrder> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<HostedNumberOrder> nextPage(final Page<HostedNumberOrder> page,
                                            final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.PREVIEW.toString())
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
    public Page<HostedNumberOrder> previousPage(final Page<HostedNumberOrder> page,
                                                final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.PREVIEW.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of HostedNumberOrder Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<HostedNumberOrder> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("HostedNumberOrder read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "items",
            response.getContent(),
            HostedNumberOrder.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (phoneNumber != null) {
            request.addQueryParam("PhoneNumber", phoneNumber.toString());
        }

        if (incomingPhoneNumberSid != null) {
            request.addQueryParam("IncomingPhoneNumberSid", incomingPhoneNumberSid);
        }

        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }

        if (uniqueName != null) {
            request.addQueryParam("UniqueName", uniqueName);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}

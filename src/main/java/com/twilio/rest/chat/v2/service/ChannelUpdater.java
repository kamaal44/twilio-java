/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.chat.v2.service;

import com.twilio.base.Updater;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

public class ChannelUpdater extends Updater<Channel> {
    private final String pathServiceSid;
    private final String pathSid;
    private String friendlyName;
    private String uniqueName;
    private String attributes;
    private DateTime dateCreated;
    private DateTime dateUpdated;
    private String createdBy;
    private Channel.WebhookEnabledType xTwilioWebhookEnabled;

    /**
     * Construct a new ChannelUpdater.
     *
     * @param pathServiceSid The SID of the Service to update the resource from
     * @param pathSid The SID of the Channel resource to update
     */
    public ChannelUpdater(final String pathServiceSid,
                          final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathSid = pathSid;
    }

    /**
     * A descriptive string that you create to describe the resource. It can be up
     * to 256 characters long..
     *
     * @param friendlyName A string to describe the resource
     * @return this
     */
    public ChannelUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * An application-defined string that uniquely identifies the resource. It can
     * be used to address the resource in place of the resource's `sid` in the URL.
     * This value must be 256 characters or less in length and unique within the
     * Service..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public ChannelUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * A valid JSON string that contains application-specific data..
     *
     * @param attributes A valid JSON string that contains application-specific data
     * @return this
     */
    public ChannelUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The date, specified in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601)
     * format, to assign to the resource as the date it was created. The default
     * value is the current time set by the Chat service.  Note that this should
     * only be used in cases where a Channel is being recreated from a
     * backup/separate source..
     *
     * @param dateCreated The ISO 8601 date and time in GMT when the resource was
     *                    created
     * @return this
     */
    public ChannelUpdater setDateCreated(final DateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    /**
     * The date, specified in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601)
     * format, to assign to the resource as the date it was last updated..
     *
     * @param dateUpdated The ISO 8601 date and time in GMT when the resource was
     *                    updated
     * @return this
     */
    public ChannelUpdater setDateUpdated(final DateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * The `identity` of the User that created the channel. Default is: `system`..
     *
     * @param createdBy The identity of the User that created the Channel
     * @return this
     */
    public ChannelUpdater setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * The X-Twilio-Webhook-Enabled HTTP request header.
     *
     * @param xTwilioWebhookEnabled The X-Twilio-Webhook-Enabled HTTP request header
     * @return this
     */
    public ChannelUpdater setXTwilioWebhookEnabled(final Channel.WebhookEnabledType xTwilioWebhookEnabled) {
        this.xTwilioWebhookEnabled = xTwilioWebhookEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Channel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Channel update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CHAT.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Channels/" + this.pathSid + ""
        );

        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Channel update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Channel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested header parameters to the Request.
     *
     * @param request Request to add header params to
     */
    private void addHeaderParams(final Request request) {
        if (xTwilioWebhookEnabled != null) {
            request.addHeaderParam("X-Twilio-Webhook-Enabled", xTwilioWebhookEnabled.toString());
        }
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }

        if (dateCreated != null) {
            request.addPostParam("DateCreated", dateCreated.toString());
        }

        if (dateUpdated != null) {
            request.addPostParam("DateUpdated", dateUpdated.toString());
        }

        if (createdBy != null) {
            request.addPostParam("CreatedBy", createdBy);
        }
    }
}

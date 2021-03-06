/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.ipmessaging.v2.service.channel;

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

public class MessageUpdater extends Updater<Message> {
    private final String pathServiceSid;
    private final String pathChannelSid;
    private final String pathSid;
    private String body;
    private String attributes;
    private DateTime dateCreated;
    private DateTime dateUpdated;
    private String lastUpdatedBy;
    private String from;
    private Message.WebhookEnabledType xTwilioWebhookEnabled;

    /**
     * Construct a new MessageUpdater.
     *
     * @param pathServiceSid The SID of the Service to update the resource from
     * @param pathChannelSid The SID of the Channel the message belongs to
     * @param pathSid The SID of the Message resource to update
     */
    public MessageUpdater(final String pathServiceSid,
                          final String pathChannelSid,
                          final String pathSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathChannelSid = pathChannelSid;
        this.pathSid = pathSid;
    }

    /**
     * The message to send to the channel. Can be an empty string or `null`, which
     * sets the value as an empty string. You can send structured data in the body
     * by serializing it as a string..
     *
     * @param body The message to send to the channel
     * @return this
     */
    public MessageUpdater setBody(final String body) {
        this.body = body;
        return this;
    }

    /**
     * A valid JSON string that contains application-specific data..
     *
     * @param attributes A valid JSON string that contains application-specific data
     * @return this
     */
    public MessageUpdater setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The date, specified in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601)
     * format, to assign to the resource as the date it was created. The default
     * value is the current time set by the Chat service. This parameter should only
     * be used when a Chat's history is being recreated from a backup/separate
     * source..
     *
     * @param dateCreated The ISO 8601 date and time in GMT when the resource was
     *                    created
     * @return this
     */
    public MessageUpdater setDateCreated(final DateTime dateCreated) {
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
    public MessageUpdater setDateUpdated(final DateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    /**
     * The [Identity](https://www.twilio.com/docs/chat/identity) of the User who
     * last updated the Message, if applicable..
     *
     * @param lastUpdatedBy The Identity of the User who last updated the Message,
     *                      if applicable
     * @return this
     */
    public MessageUpdater setLastUpdatedBy(final String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    /**
     * The [Identity](https://www.twilio.com/docs/chat/identity) of the message's
     * author..
     *
     * @param from The Identity of the message's author
     * @return this
     */
    public MessageUpdater setFrom(final String from) {
        this.from = from;
        return this;
    }

    /**
     * The X-Twilio-Webhook-Enabled HTTP request header.
     *
     * @param xTwilioWebhookEnabled The X-Twilio-Webhook-Enabled HTTP request header
     * @return this
     */
    public MessageUpdater setXTwilioWebhookEnabled(final Message.WebhookEnabledType xTwilioWebhookEnabled) {
        this.xTwilioWebhookEnabled = xTwilioWebhookEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Message
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Message update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.IPMESSAGING.toString(),
            "/v2/Services/" + this.pathServiceSid + "/Channels/" + this.pathChannelSid + "/Messages/" + this.pathSid + ""
        );

        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Message update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Message.fromJson(response.getStream(), client.getObjectMapper());
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
        if (body != null) {
            request.addPostParam("Body", body);
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

        if (lastUpdatedBy != null) {
            request.addPostParam("LastUpdatedBy", lastUpdatedBy);
        }

        if (from != null) {
            request.addPostParam("From", from);
        }
    }
}
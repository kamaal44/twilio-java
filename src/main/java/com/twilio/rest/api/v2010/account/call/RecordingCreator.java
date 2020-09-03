/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.call;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;
import java.util.List;

public class RecordingCreator extends Creator<Recording> {
    private String pathAccountSid;
    private final String pathCallSid;
    private List<String> recordingStatusCallbackEvent;
    private URI recordingStatusCallback;
    private HttpMethod recordingStatusCallbackMethod;
    private String trim;
    private String recordingChannels;

    /**
     * Construct a new RecordingCreator.
     *
     * @param pathCallSid The SID of the Call to associate the resource with
     */
    public RecordingCreator(final String pathCallSid) {
        this.pathCallSid = pathCallSid;
    }

    /**
     * Construct a new RecordingCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param pathCallSid The SID of the Call to associate the resource with
     */
    public RecordingCreator(final String pathAccountSid,
                            final String pathCallSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathCallSid = pathCallSid;
    }

    /**
     * The recording status events on which we should call the
     * `recording_status_callback` URL. Can be: `in-progress`, `completed` and
     * `absent` and the default is `completed`. Separate multiple event values with
     * a space..
     *
     * @param recordingStatusCallbackEvent The recording status changes that should
     *                                     generate a callback
     * @return this
     */
    public RecordingCreator setRecordingStatusCallbackEvent(final List<String> recordingStatusCallbackEvent) {
        this.recordingStatusCallbackEvent = recordingStatusCallbackEvent;
        return this;
    }

    /**
     * The recording status events on which we should call the
     * `recording_status_callback` URL. Can be: `in-progress`, `completed` and
     * `absent` and the default is `completed`. Separate multiple event values with
     * a space..
     *
     * @param recordingStatusCallbackEvent The recording status changes that should
     *                                     generate a callback
     * @return this
     */
    public RecordingCreator setRecordingStatusCallbackEvent(final String recordingStatusCallbackEvent) {
        return setRecordingStatusCallbackEvent(Promoter.listOfOne(recordingStatusCallbackEvent));
    }

    /**
     * The URL we should call using the `recording_status_callback_method` on each
     * recording event specified in  `recording_status_callback_event`. For more
     * information, see [RecordingStatusCallback
     * parameters](https://www.twilio.com/docs/voice/api/recording#recordingstatuscallback)..
     *
     * @param recordingStatusCallback The callback URL on each selected recording
     *                                event
     * @return this
     */
    public RecordingCreator setRecordingStatusCallback(final URI recordingStatusCallback) {
        this.recordingStatusCallback = recordingStatusCallback;
        return this;
    }

    /**
     * The URL we should call using the `recording_status_callback_method` on each
     * recording event specified in  `recording_status_callback_event`. For more
     * information, see [RecordingStatusCallback
     * parameters](https://www.twilio.com/docs/voice/api/recording#recordingstatuscallback)..
     *
     * @param recordingStatusCallback The callback URL on each selected recording
     *                                event
     * @return this
     */
    public RecordingCreator setRecordingStatusCallback(final String recordingStatusCallback) {
        return setRecordingStatusCallback(Promoter.uriFromString(recordingStatusCallback));
    }

    /**
     * The HTTP method we should use to call `recording_status_callback`. Can be:
     * `GET` or `POST` and the default is `POST`..
     *
     * @param recordingStatusCallbackMethod The HTTP method we should use to call
     *                                      `recording_status_callback`
     * @return this
     */
    public RecordingCreator setRecordingStatusCallbackMethod(final HttpMethod recordingStatusCallbackMethod) {
        this.recordingStatusCallbackMethod = recordingStatusCallbackMethod;
        return this;
    }

    /**
     * Whether to trim any leading and trailing silence in the recording. Can be:
     * `trim-silence` or `do-not-trim` and the default is `do-not-trim`.
     * `trim-silence` trims the silence from the beginning and end of the recording
     * and `do-not-trim` does not..
     *
     * @param trim Whether to trim the silence in the recording
     * @return this
     */
    public RecordingCreator setTrim(final String trim) {
        this.trim = trim;
        return this;
    }

    /**
     * The number of channels used in the recording. Can be: `mono` or `dual` and
     * the default is `mono`. `mono` records all parties of the call into one
     * channel. `dual` records each party of a 2-party call into separate channels..
     *
     * @param recordingChannels The number of channels that the output recording
     *                          will be configured with
     * @return this
     */
    public RecordingCreator setRecordingChannels(final String recordingChannels) {
        this.recordingChannels = recordingChannels;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Recording
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Recording create(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Calls/" + this.pathCallSid + "/Recordings.json"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Recording creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Recording.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (recordingStatusCallbackEvent != null) {
            for (String prop : recordingStatusCallbackEvent) {
                request.addPostParam("RecordingStatusCallbackEvent", prop);
            }
        }

        if (recordingStatusCallback != null) {
            request.addPostParam("RecordingStatusCallback", recordingStatusCallback.toString());
        }

        if (recordingStatusCallbackMethod != null) {
            request.addPostParam("RecordingStatusCallbackMethod", recordingStatusCallbackMethod.toString());
        }

        if (trim != null) {
            request.addPostParam("Trim", trim);
        }

        if (recordingChannels != null) {
            request.addPostParam("RecordingChannels", recordingChannels);
        }
    }
}

/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace.worker;

import com.twilio.base.Fetcher;
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

public class WorkersStatisticsFetcher extends Fetcher<WorkersStatistics> {
    private final String pathWorkspaceSid;
    private Integer minutes;
    private DateTime startDate;
    private DateTime endDate;
    private String taskQueueSid;
    private String taskQueueName;
    private String friendlyName;
    private String taskChannel;

    /**
     * Construct a new WorkersStatisticsFetcher.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the Worker to fetch
     */
    public WorkersStatisticsFetcher(final String pathWorkspaceSid) {
        this.pathWorkspaceSid = pathWorkspaceSid;
    }

    /**
     * Only calculate statistics since this many minutes in the past. The default 15
     * minutes. This is helpful for displaying statistics for the last 15 minutes,
     * 240 minutes (4 hours), and 480 minutes (8 hours) to see trends..
     *
     * @param minutes Only calculate statistics since this many minutes in the past
     * @return this
     */
    public WorkersStatisticsFetcher setMinutes(final Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * Only calculate statistics from this date and time and later, specified in
     * [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) format..
     *
     * @param startDate Only calculate statistics from on or after this date
     * @return this
     */
    public WorkersStatisticsFetcher setStartDate(final DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Only calculate statistics from this date and time and earlier, specified in
     * GMT as an [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) date-time..
     *
     * @param endDate Only calculate statistics from this date and time and earlier
     * @return this
     */
    public WorkersStatisticsFetcher setEndDate(final DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * The SID of the TaskQueue for which to fetch Worker statistics..
     *
     * @param taskQueueSid The SID of the TaskQueue for which to fetch Worker
     *                     statistics
     * @return this
     */
    public WorkersStatisticsFetcher setTaskQueueSid(final String taskQueueSid) {
        this.taskQueueSid = taskQueueSid;
        return this;
    }

    /**
     * The `friendly_name` of the TaskQueue for which to fetch Worker statistics..
     *
     * @param taskQueueName The friendly_name of the TaskQueue for which to fetch
     *                      Worker statistics
     * @return this
     */
    public WorkersStatisticsFetcher setTaskQueueName(final String taskQueueName) {
        this.taskQueueName = taskQueueName;
        return this;
    }

    /**
     * Only include Workers with `friendly_name` values that match this parameter..
     *
     * @param friendlyName Only include Workers with `friendly_name` values that
     *                     match this parameter
     * @return this
     */
    public WorkersStatisticsFetcher setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Only calculate statistics on this TaskChannel. Can be the TaskChannel's SID
     * or its `unique_name`, such as `voice`, `sms`, or `default`..
     *
     * @param taskChannel Only calculate statistics on this TaskChannel
     * @return this
     */
    public WorkersStatisticsFetcher setTaskChannel(final String taskChannel) {
        this.taskChannel = taskChannel;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched WorkersStatistics
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public WorkersStatistics fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.pathWorkspaceSid + "/Workers/Statistics"
        );

        addQueryParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("WorkersStatistics fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return WorkersStatistics.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (minutes != null) {
            request.addQueryParam("Minutes", minutes.toString());
        }

        if (startDate != null) {
            request.addQueryParam("StartDate", startDate.toString());
        }

        if (endDate != null) {
            request.addQueryParam("EndDate", endDate.toString());
        }

        if (taskQueueSid != null) {
            request.addQueryParam("TaskQueueSid", taskQueueSid);
        }

        if (taskQueueName != null) {
            request.addQueryParam("TaskQueueName", taskQueueName);
        }

        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }

        if (taskChannel != null) {
            request.addQueryParam("TaskChannel", taskChannel);
        }
    }
}

/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1;

import com.twilio.base.Updater;
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

public class WorkspaceUpdater extends Updater<Workspace> {
    private final String pathSid;
    private String defaultActivitySid;
    private URI eventCallbackUrl;
    private String eventsFilter;
    private String friendlyName;
    private Boolean multiTaskEnabled;
    private String timeoutActivitySid;
    private Workspace.QueueOrder prioritizeQueueOrder;

    /**
     * Construct a new WorkspaceUpdater.
     *
     * @param pathSid The SID of the resource to update
     */
    public WorkspaceUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * The SID of the Activity that will be used when new Workers are created in the
     * Workspace..
     *
     * @param defaultActivitySid The SID of the Activity that will be used when new
     *                           Workers are created in the Workspace
     * @return this
     */
    public WorkspaceUpdater setDefaultActivitySid(final String defaultActivitySid) {
        this.defaultActivitySid = defaultActivitySid;
        return this;
    }

    /**
     * The URL we should call when an event occurs. See [Workspace
     * Events](https://www.twilio.com/docs/taskrouter/api/event) for more
     * information..
     *
     * @param eventCallbackUrl The URL we should call when an event occurs
     * @return this
     */
    public WorkspaceUpdater setEventCallbackUrl(final URI eventCallbackUrl) {
        this.eventCallbackUrl = eventCallbackUrl;
        return this;
    }

    /**
     * The URL we should call when an event occurs. See [Workspace
     * Events](https://www.twilio.com/docs/taskrouter/api/event) for more
     * information..
     *
     * @param eventCallbackUrl The URL we should call when an event occurs
     * @return this
     */
    public WorkspaceUpdater setEventCallbackUrl(final String eventCallbackUrl) {
        return setEventCallbackUrl(Promoter.uriFromString(eventCallbackUrl));
    }

    /**
     * The list of Workspace events for which to call event_callback_url. For
     * example if `EventsFilter=task.created,task.canceled,worker.activity.update`,
     * then TaskRouter will call event_callback_url only when a task is created,
     * canceled, or a Worker activity is updated..
     *
     * @param eventsFilter The list of Workspace events for which to call
     *                     event_callback_url
     * @return this
     */
    public WorkspaceUpdater setEventsFilter(final String eventsFilter) {
        this.eventsFilter = eventsFilter;
        return this;
    }

    /**
     * A descriptive string that you create to describe the Workspace resource. For
     * example: `Sales Call Center` or `Customer Support Team`..
     *
     * @param friendlyName A string to describe the Workspace resource
     * @return this
     */
    public WorkspaceUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Whether to enable multi-tasking. Can be: `true` to enable multi-tasking, or
     * `false` to disable it. The default is `false`. Multi-tasking allows Workers
     * to handle multiple Tasks simultaneously. When enabled (`true`), each Worker
     * can receive parallel reservations up to the per-channel maximums defined in
     * the Workers section. Otherwise, each Worker will only receive a new
     * reservation when the previous task is completed. Learn more at
     * [Multitasking][https://www.twilio.com/docs/taskrouter/multitasking]..
     *
     * @param multiTaskEnabled Whether multi-tasking is enabled
     * @return this
     */
    public WorkspaceUpdater setMultiTaskEnabled(final Boolean multiTaskEnabled) {
        this.multiTaskEnabled = multiTaskEnabled;
        return this;
    }

    /**
     * The SID of the Activity that will be assigned to a Worker when a Task
     * reservation times out without a response..
     *
     * @param timeoutActivitySid The SID of the Activity that will be assigned to a
     *                           Worker when a Task reservation times out without a
     *                           response
     * @return this
     */
    public WorkspaceUpdater setTimeoutActivitySid(final String timeoutActivitySid) {
        this.timeoutActivitySid = timeoutActivitySid;
        return this;
    }

    /**
     * The type of TaskQueue to prioritize when Workers are receiving Tasks from
     * both types of TaskQueues. Can be: `LIFO` or `FIFO` and the default is `FIFO`.
     * For more information, see [Queue
     * Ordering][https://www.twilio.com/docs/taskrouter/queue-ordering-last-first-out-lifo]..
     *
     * @param prioritizeQueueOrder The type of TaskQueue to prioritize when Workers
     *                             are receiving Tasks from both types of TaskQueues
     * @return this
     */
    public WorkspaceUpdater setPrioritizeQueueOrder(final Workspace.QueueOrder prioritizeQueueOrder) {
        this.prioritizeQueueOrder = prioritizeQueueOrder;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Workspace
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Workspace update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Workspace update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Workspace.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (defaultActivitySid != null) {
            request.addPostParam("DefaultActivitySid", defaultActivitySid);
        }

        if (eventCallbackUrl != null) {
            request.addPostParam("EventCallbackUrl", eventCallbackUrl.toString());
        }

        if (eventsFilter != null) {
            request.addPostParam("EventsFilter", eventsFilter);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (multiTaskEnabled != null) {
            request.addPostParam("MultiTaskEnabled", multiTaskEnabled.toString());
        }

        if (timeoutActivitySid != null) {
            request.addPostParam("TimeoutActivitySid", timeoutActivitySid);
        }

        if (prioritizeQueueOrder != null) {
            request.addPostParam("PrioritizeQueueOrder", prioritizeQueueOrder.toString());
        }
    }
}

/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1.room.participant;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class SubscribedTrackReader extends Reader<SubscribedTrack> {
    private final String pathRoomSid;
    private final String pathParticipantSid;

    /**
     * Construct a new SubscribedTrackReader.
     *
     * @param pathRoomSid The SID of the Room resource with the Track resources to
     *                    read
     * @param pathParticipantSid The SID of the participant that subscribes to the
     *                           Track resources to read
     */
    public SubscribedTrackReader(final String pathRoomSid,
                                 final String pathParticipantSid) {
        this.pathRoomSid = pathRoomSid;
        this.pathParticipantSid = pathParticipantSid;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return SubscribedTrack ResourceSet
     */
    @Override
    public ResourceSet<SubscribedTrack> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return SubscribedTrack ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<SubscribedTrack> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.VIDEO.toString(),
            "/v1/Rooms/" + this.pathRoomSid + "/Participants/" + this.pathParticipantSid + "/SubscribedTracks"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return SubscribedTrack ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<SubscribedTrack> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<SubscribedTrack> nextPage(final Page<SubscribedTrack> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.VIDEO.toString())
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
    public Page<SubscribedTrack> previousPage(final Page<SubscribedTrack> page,
                                              final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.VIDEO.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of SubscribedTrack Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<SubscribedTrack> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SubscribedTrack read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "subscribed_tracks",
            response.getContent(),
            SubscribedTrack.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}

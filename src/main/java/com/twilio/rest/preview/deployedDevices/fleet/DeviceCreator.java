/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.deployedDevices.fleet;

import com.twilio.base.Creator;
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
public class DeviceCreator extends Creator<Device> {
    private final String pathFleetSid;
    private String uniqueName;
    private String friendlyName;
    private String identity;
    private String deploymentSid;
    private Boolean enabled;

    /**
     * Construct a new DeviceCreator.
     *
     * @param pathFleetSid The fleet_sid
     */
    public DeviceCreator(final String pathFleetSid) {
        this.pathFleetSid = pathFleetSid;
    }

    /**
     * Provides a unique and addressable name to be assigned to this Device, to be
     * used in addition to SID, up to 128 characters long..
     *
     * @param uniqueName A unique, addressable name of this Device.
     * @return this
     */
    public DeviceCreator setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * Provides a human readable descriptive text to be assigned to this Device, up
     * to 256 characters long..
     *
     * @param friendlyName A human readable description for this Device.
     * @return this
     */
    public DeviceCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Provides an arbitrary string identifier representing a human user to be
     * associated with this Device, up to 256 characters long..
     *
     * @param identity An identifier of the Device user.
     * @return this
     */
    public DeviceCreator setIdentity(final String identity) {
        this.identity = identity;
        return this;
    }

    /**
     * Specifies the unique string identifier of the Deployment group that this
     * Device is going to be associated with..
     *
     * @param deploymentSid The unique SID of the Deployment group.
     * @return this
     */
    public DeviceCreator setDeploymentSid(final String deploymentSid) {
        this.deploymentSid = deploymentSid;
        return this;
    }

    /**
     * The enabled.
     *
     * @param enabled The enabled
     * @return this
     */
    public DeviceCreator setEnabled(final Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Device
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Device create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/DeployedDevices/Fleets/" + this.pathFleetSid + "/Devices"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Device creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Device.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (identity != null) {
            request.addPostParam("Identity", identity);
        }

        if (deploymentSid != null) {
            request.addPostParam("DeploymentSid", deploymentSid);
        }

        if (enabled != null) {
            request.addPostParam("Enabled", enabled.toString());
        }
    }
}

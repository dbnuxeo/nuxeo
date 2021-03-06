/*
 * (C) Copyright 2016 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Ricardo Dias
 */
package org.nuxeo.ecm.platform.video.tools;

import org.nuxeo.ecm.core.api.Blob;
import java.util.List;

/**
 * Service that allows the execution of different operations in video blobs through the execution of {@link VideoTool}.
 *
 * @since 8.4
 */
public interface VideoToolsService {

    /**
     * Extracts the closed captions from a video blob.
     *
     * @param video the input blob
     * @param outputFormat the outformat of the captions (srt, txt, ttxt is the default)
     * @param startAt the start time in format "xx:xx"
     * @param endAt the end time in format "xx:xx"
     * @return the closed captions if any were found
     */
    Blob extractClosedCaptions(Blob video, String outputFormat, String startAt, String endAt);

    /**
     * Concat the input video blobs into a single video blob.
     *
     * @param videos
     * @return video blob with the videos concatenated
     */
    Blob concat(List<Blob> videos);

    /**
     * Slices a video blob from a start time and the input duration.
     * If start it empty, the blob will be sliced in n-parts with similar duration.
     * If duration is empty, the video blob will be sliced from startAt until the end.
     *
     * @param video the input blob
     * @param startAt the start time in "xx:xx" format
     * @param duration the duration of the sliced blob in seconds
     * @param encode option to re-encode the ouptut video blob
     * @return video blobs generated by the slicer
     */
    List<Blob> slice(Blob video, String startAt, String duration, boolean encode);

    /**
     * Add a watermark to a video blob.
     *
     * @param video the input blob
     * @param picture the picture blob to be used as the watermark
     * @param x the x offset starting from the left
     * @param y the y offset starting from the top
     * @return a video blob with a watermark at the position specified
     */
    Blob watermark(Blob video, Blob picture, String x, String y);

    /**
     * Checks if a determined tool is available.
     *
     * @param toolName the name of the tool
     * @return true if the tool is available or false otherwise
     */
    boolean isToolAvailable(String toolName);
}

/**
 * Licensed to the Rhiot under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rhiot.component.webcam;

/**
 * Constants Class
 */
public class WebcamConstants {
    
    public static final String WEBCAM_MOTION_EVENT_HEADER = "io.rhiot.webcam.webcamMotionEvent";

    public static final String V4L2_WEBCAM_LOADING_COMMAND = "modprobe bcm2835-v4l2";
    
    private WebcamConstants() {
        // Constants class
    }
}

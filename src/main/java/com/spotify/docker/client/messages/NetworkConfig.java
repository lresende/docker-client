/*
 * Copyright (c) 2014 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.docker.client.messages;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class NetworkConfig {

  @JsonProperty("Name") private String name;
  @JsonProperty("Driver") private String driver;
  @JsonProperty("IPAM") private Ipam ipam;
  @JsonProperty("Options") private Map<String, String> options;
  @JsonProperty("CheckDuplicate") private boolean checkDuplicate;

  private NetworkConfig(final Builder builder) {
    this.name = builder.name;
    this.options = builder.options;
    this.driver = builder.driver;
    this.ipam = builder.ipam;
    this.checkDuplicate = builder.checkDuplicate;
  }

  public String name() {
    return name;
  }

  public String driver() {
    return driver;
  }

  public Ipam ipam() {
    return ipam;
  }

  public Map<String, String> options() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    NetworkConfig that = (NetworkConfig) o;

    if (checkDuplicate != that.checkDuplicate) {
      return false;
    }
    if (!name.equals(that.name)) {
      return false;
    }
    if (driver != null ? !driver.equals(that.driver) : that.driver != null) {
      return false;
    }
    if (ipam != null ? !ipam.equals(that.ipam) : that.ipam != null) {
      return false;
    }
    return !(options != null ? !options.equals(that.options) : that.options != null);

  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (driver != null ? driver.hashCode() : 0);
    result = 31 * result + (ipam != null ? ipam.hashCode() : 0);
    result = 31 * result + (options != null ? options.hashCode() : 0);
    result = 31 * result + (checkDuplicate ? 1 : 0);
    return result;
  }


  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("name", name).add("driver", driver)
        .add("options", options).add("checkDuplicate", checkDuplicate)
        .add("ipam", ipam).toString();
  }

  public static class Builder {

    private String name;
    private Map<String, String> options = new HashMap<String, String>();
    private String driver;
    private Ipam ipam;
    public boolean checkDuplicate;

    private Builder() {
    }

    public Builder name(final String name) {
      if (name != null && !name.isEmpty()) {
        this.name = name;
      }
      return this;
    }

    public Builder option(final String key, final String value) {
      if (key != null && !key.isEmpty()) {
        this.options.put(key, value);
      }
      return this;
    }

    public Builder ipam(final Ipam ipam) {
      if (ipam != null) {
        this.ipam = ipam;
      }
      return this;
    }

    public Builder driver(final String driver) {
      if (driver != null && !driver.isEmpty()) {
        this.driver = driver;
      }
      return this;
    }

    public Builder checkDuplicate(boolean check) {
      this.checkDuplicate = check;
      return this;
    }

    public NetworkConfig build() {
      return new NetworkConfig(this);
    }

  }

}

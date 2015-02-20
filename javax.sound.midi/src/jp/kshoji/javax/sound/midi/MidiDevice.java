package jp.kshoji.javax.sound.midi;

import java.util.List;

/**
 * Interface for MIDI Device
 *
 * @author K.Shoji
 */
public interface MidiDevice {

    /**
     * Get the device information
     *
     * @return the device information
     */
	Info getDeviceInfo();

	/**
	 * Open the {@link MidiDevice}. This method must be called at getting the new instance.
	 * 
	 * @throws MidiUnavailableException
	 */
	void open() throws MidiUnavailableException;

	/**
	 * Close the {@link MidiDevice}. This method must be called at finishing to use the instance.
	 */
	void close();

	/**
	 * Check if the {@link MidiDevice} opened.
	 * 
	 * @return true if already opened
	 */
	boolean isOpen();

	/**
	 * Get the {@link MidiDevice}'s timeStamp.
	 * @return -1 if the timeStamp not supported.
	 */
	long getMicrosecondPosition();

	/**
	 * Get the number of the {@link Receiver}s. 
	 * 
	 * @return the number of the {@link Receiver}s.
	 */
	int getMaxReceivers();

	/**
	 * Get the number of the {@link Transmitter}s. 
	 * 
	 * @return the number of the {@link Transmitter}s.
	 */
	int getMaxTransmitters();

	/**
	 * Get the default {@link Receiver}.
	 * 
	 * @return the default {@link Receiver}.
	 * @throws MidiUnavailableException
	 */
	Receiver getReceiver() throws MidiUnavailableException;

	/**
	 * Get the all of {@link Receiver}s.
	 * 
	 * @return the all of {@link Receiver}s.
	 */
	List<Receiver> getReceivers();

	/**
	 * Get the default {@link Transmitter}.
	 * 
	 * @return the default {@link Transmitter}.
	 * @throws MidiUnavailableException
	 */
	Transmitter getTransmitter() throws MidiUnavailableException;

	/**
	 * Get the all of {@link Transmitter}s.
	 * 
	 * @return the all of {@link Transmitter}s.
	 */
	List<Transmitter> getTransmitters();

	/**
	 * Represents the {@link MidiDevice}'s information
	 *
	 * @author K.Shoji
	 */
	public static class Info {
		private String name;
		private String vendor;
		private String description;
		private String version;

		public Info(String name, String vendor, String description, String version) {
			this.name = name;
			this.vendor = vendor;
			this.description = description;
			this.version = version;
		}

		/**
		 * Get the name of {@link MidiDevice}
		 * 
		 * @return the name of {@link MidiDevice}
		 */
		public final String getName() {
			return name;
		}

		/**
		 * Get the vendor of {@link MidiDevice}
		 * 
		 * @return the vendor of {@link MidiDevice}
		 */
		public final String getVendor() {
			return vendor;
		}

		/**
		 * Get the description of {@link MidiDevice}
		 * 
		 * @return the description of {@link MidiDevice}
		 */
		public final String getDescription() {
			return description;
		}

		/**
		 * Get the version of {@link MidiDevice}
		 * 
		 * @return the version of {@link MidiDevice}
		 */
		public final String getVersion() {
			return version;
		}

		@Override
		public final String toString() {
			return name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
			result = prime * result + ((version == null) ? 0 : version.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final Info other = (Info) obj;
			if (description == null) {
				if (other.description != null) {
					return false;
				}
			} else if (!description.equals(other.description)) {
				return false;
			}
			if (name == null) {
				if (other.name != null) {
					return false;
				}
			} else if (!name.equals(other.name)) {
				return false;
			}
			if (vendor == null) {
				if (other.vendor != null) {
					return false;
				}
			} else if (!vendor.equals(other.vendor)) {
				return false;
			}
			if (version == null) {
				if (other.version != null) {
					return false;
				}
			} else if (!version.equals(other.version)) {
				return false;
			}
			return true;
		}
	}
}

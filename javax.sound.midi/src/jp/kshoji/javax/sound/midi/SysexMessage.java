package jp.kshoji.javax.sound.midi;

/**
 * Represents MIDI SysEx Message
 * 
 * @author K.Shoji
 */
public class SysexMessage extends MidiMessage {

	/**
	 * Default constructor.
	 */
	public SysexMessage() {
		this(new byte[] { (byte) (ShortMessage.START_OF_EXCLUSIVE & 0xff), (byte) (ShortMessage.END_OF_EXCLUSIVE & 0xff) });
	}

	/**
	 * Constructor with raw data.
	 * 
	 * @param data the SysEx data
	 */
	protected SysexMessage(byte[] data) {
		super(data);
	}

    /**
     * Constructor with raw data and length.
     *
     * @param data the SysEx data
     * @param length the data length
     * @throws InvalidMidiDataException
     */
	public SysexMessage(byte[] data, int length)
			throws InvalidMidiDataException {
		super(null);
		setMessage(data, length);
	}

	@Override
	public void setMessage(byte[] data, int length) throws InvalidMidiDataException {
		int status = (data[0] & 0xff);
		if ((status != ShortMessage.START_OF_EXCLUSIVE) && (status != ShortMessage.END_OF_EXCLUSIVE)) {
			throw new InvalidMidiDataException("Invalid status byte for SysexMessage: 0x" + Integer.toHexString(status));
		}
		super.setMessage(data, length);
	}

	/**
	 * Set the entire information of message.
	 * 
	 * @param status must be ShortMessage.START_OF_EXCLUSIVE or ShortMessage.END_OF_EXCLUSIVE
	 * @param data the SysEx data
	 * @param length unused parameter. Use always data.length
	 * @throws InvalidMidiDataException
	 */
	public void setMessage(int status, byte[] data, int length) throws InvalidMidiDataException {
		if ((status != ShortMessage.START_OF_EXCLUSIVE) && (status != ShortMessage.END_OF_EXCLUSIVE)) {
			throw new InvalidMidiDataException("Invalid status byte for SysexMessage: 0x" + Integer.toHexString(status));
		}

		// extend 1 byte
		this.data = new byte[data.length + 1];

		this.data[0] = (byte) (status & 0xff);
		if (data.length > 0) {
			System.arraycopy(data, 0, this.data, 1, data.length);
		}
	}

	/**
	 * Get the SysEx data.
	 * 
	 * @return SysEx data
	 */
	public byte[] getData() {
		byte[] result = new byte[data.length];
		System.arraycopy(data, 0, result, 0, result.length);
		return result;
	}

	@Override
	public Object clone() {
		return new SysexMessage(getData());
	}
}

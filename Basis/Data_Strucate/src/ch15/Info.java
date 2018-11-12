package ch15;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月30日 下午4:56:58
 * @类说明:员工表
 */
public class Info {
	private String key;
	private String name;

	public Info(String key, String name) {
		this.key = key;
		this.name = name;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}

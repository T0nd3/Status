package faeuster.pro.status;

public enum Units {
private String unitName;

	BYTE("BYTE"), KILOBYTE("KB"), MEGABYTE("MB"), GIGABYTE("GB"), TERRABYTE("TB");

	Units(String unitName) {
		this.unitName = unitName;
	}

	public String getName() {
		return this.unitName;
	}

}

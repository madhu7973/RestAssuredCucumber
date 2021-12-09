package pojo;

import java.util.List;

public class OAuth2Courses {

	private List<OAuth2WebAutomation> webAutomation;
	private List<OAuth2Api> api;
	private List<OAuth2Mobile> mobile;

	public List<OAuth2WebAutomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<OAuth2WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<OAuth2Api> getApi() {
		return api;
	}

	public void setApi(List<OAuth2Api> api) {
		this.api = api;
	}

	public List<OAuth2Mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<OAuth2Mobile> mobile) {
		this.mobile = mobile;
	}

}

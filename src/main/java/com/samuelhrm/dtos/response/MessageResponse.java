package com.samuelhrm.dtos.response;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@RequiredArgsConstructor
public class  MessageResponse {
	private String message;
	private String successfully;
	private Long savedEntitylongId;
	private int savedEntityintId;

	private Long diagnosisid;
	private String diagnosistreatmentType;
	private String diagnosisdrugDescription;

	public MessageResponse(String message, String successfully ){
		this.message = message;
		this.successfully = successfully;
	}

	public MessageResponse(String message, String successfully, long savedEntitylongId ){
		this.message = message;
		this.successfully = successfully;
		this.savedEntitylongId = savedEntitylongId;
	}

    public MessageResponse(String message) {
		this.message = message;
    }
}

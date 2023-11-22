package com.mashreq.validator.service;

import org.springframework.stereotype.Service;

import com.mashreq.validator.model.TitleInput;
import com.mashreq.validator.model.TitleValidatorOutput;
import com.mashreq.validator.util.CommonService;
import com.mashreq.validator.util.EntityServices;
import com.mashreq.validator.util.IndividualServices;

@Service
public class NameValidatorServiceImpl implements NameValidatorService {

	public static final String pote_match = "Potentially Matched";
	public static final String matched = "Matched";
	public static final String error = "error";
	public static final String not_match = "Not Matched";
	public static final String Default_Language = "eng";
	CommonService commonService = new CommonService();
	
	IndividualServices individualServices=new IndividualServices();
	
	EntityServices entityServices = new EntityServices();
	TitleValidatorOutput titleValidatorOutput;

	public NameValidatorServiceImpl() {
		super();
		this.titleValidatorOutput = new TitleValidatorOutput();
	}

	@Override
	public TitleValidatorOutput mainValidator(TitleInput entityInput) throws Exception {
		// TODO Auto-generated method stub

		if (commonService.compareStrings(entityInput.getPaymentcopy().getEntityType(),
				entityInput.getWatchlist().getEntityType())) {
			if (commonService.compareStrings(entityInput.getPaymentcopy().getLanguage(),
					entityInput.getWatchlist().getLanguage())) {
				switch (entityInput.getPaymentcopy().getEntityType().toUpperCase()) {

				case "INDIVIDUAL":

					return individualValidator(entityInput);
				case "ENTITY":

					return entityValidator(entityInput);
//				case "ADDRESS":
//
//					return addressValidator(entityInput);
				default:
					

					return  makeDesition(0.0,error,"Entity Type Not Valied!");
				}

			} else {
				
				return makeDesition(0.0,error,"Language should match!");
			}

		} else {
			

			return makeDesition(0.0,error,"EntityType should match!");
		}

	}

	public TitleValidatorOutput individualValidator(TitleInput entityInput)  {

		String inLanguage = entityInput.getPaymentcopy().getLanguage();
		String text1 = entityInput.getPaymentcopy().getText();
		String text2 = entityInput.getWatchlist().getText();
		// other than eng language Fuzzy jar will take the decision
		if (!inLanguage.equalsIgnoreCase(Default_Language)) {
			return commonService.diffLanguageFuzzyJar(text1, text2);
		} else {
			if (commonService.compareStrings(text1, text2)) { // plain match
				return makeDesition(100.0, matched, "Direct match");

			} else if (commonService.comparePhonetically(text1, text2)) { // comparing phonetically
				return makeDesition(100.0, matched, "Direct phonetic match");
			} else {
				String ctext1 = commonService.cleanString(text1);// remove duplicate words,to lower case, remove special
																	// charectors, remove extra space
				String ctext2 = commonService.cleanString(text2);// remove duplicate words,to lower case, remove special
																	// charectors, remove extra space
				if (CommonService.comparePhonetically(ctext1, ctext2)) {
					return makeDesition(100.0, matched, "Direct phonetic match after cleaning text");
				} else {
					if (individualServices.areJumbledWordsPhoneticallyMatching(ctext1, ctext2)) {
						return makeDesition(100.0, matched, "Direct phonetic match, jumbled words");
					} else {
						String[] pc = ctext1.split("\\s+");
						String[] wl = ctext2.split("\\s+");
						if (pc.length <= wl.length) {
							String arText1 = CommonService.replaceAbbreviations(ctext1);// replacing abbrevations
							String arText2 = CommonService.replaceAbbreviations(ctext2);//replacing abbrevations
							if (commonService.comparePhonetically(arText1, arText2)) {
								return makeDesition(100.0, matched,
										"Direct phonetic match after replacing abbrivations");
							} else {

								return commonService.diffLanguageFuzzyJar(arText1, arText2);// % based on levenshtein distance algorithm
							}

						} else {
							return makeDesition(0.0, not_match, "payment copy words length not matched");
						}
					}
				}

			}

		}

	}

	public TitleValidatorOutput entityValidator(TitleInput entityInput)  {
		
		String inLanguage = entityInput.getPaymentcopy().getLanguage();
		String text1 = entityInput.getPaymentcopy().getText();
		String text2 = entityInput.getWatchlist().getText();
		// other than eng language Fuzzy jar will take the decision
		if (!inLanguage.equalsIgnoreCase(Default_Language)) {
			return commonService.diffLanguageFuzzyJar(text1, text2);
		} else {
			if (commonService.compareStrings(text1, text2)) { // plain match
				return makeDesition(100.0, matched, "Direct match");

			} else if (commonService.comparePhonetically(text1, text2)) { // comparing phonetically
				return makeDesition(100.0, matched, "Direct phonetic match");
			} else {
				String ctext1=entityServices.cleaningStings(text1);
				String ctext2=entityServices.cleaningStings(text2);
				if (commonService.comparePhonetically(ctext1, ctext2)) { 
					return makeDesition(100.0, matched, "Direct phonetic match after cleaning");
				}else if (entityServices.firstWordMatchPhonetically(ctext1, ctext2)) {
					return makeDesition(100.0, matched, "First word phonetic match");
				}else if (entityServices.firstCharectorMatch(ctext1, ctext2)) {
					return makeDesition(100.0, matched, "First char match");
				}else {
					return makeDesition(0.0, not_match, "Not matched");
				}
			}
		}
		
	}
	
	public TitleValidatorOutput makeDesition(Double score, String Status, String Comment) {
		
		titleValidatorOutput.setScore(score);
		titleValidatorOutput.setStatus(Status);
		titleValidatorOutput.setComment(Comment);
		
		return titleValidatorOutput;
		
	}

}

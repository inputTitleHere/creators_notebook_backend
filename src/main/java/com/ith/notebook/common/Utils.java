package com.ith.notebook.common;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

  /**
   * 기존 이미지 이름의 확장자를 딴 다음 새로운 이미지파일 이름을 생성한다.
   * 신규 이름은 현재시간 + 3자리 난수로 구성된다.
   * @param originalName
   * @return
   */
  public String generateImageName(String originalName){
    String ext = originalName.substring(originalName.lastIndexOf('.')+1);
    LocalDateTime ldt = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    return String.format("%s_%s.%s",dtf.format(ldt),rng(),ext);
  }

  /**
   * ### 3글자의 무작위 난수(0~9)를 생성한다.
   * - 예 : "235"
   * @return
   */
  public String rng(){
    StringBuilder sb = new StringBuilder();
    for(int i=0;i<3;i++){
      sb.append((int)(Math.random()*10));
    }
    return sb.toString();
  }
}

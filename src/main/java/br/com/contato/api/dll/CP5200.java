package br.com.contato.api.dll;


import com.sun.jna.Library;
import com.sun.jna.Native;

public interface CP5200 extends Library {
    CP5200 INSTANCE = (CP5200) Native.loadLibrary("CP5200", CP5200.class);

    Integer CP5200_Program_Create(Integer width, Integer height, Byte BYTE);

    Long CP5200_Program_Destroy(Long hObj);

    Integer CP5200_Program_AddPlayWindow(Long hObj, Integer x, Integer y, Integer cx, Integer cy);

    Integer CP5200_Program_SetWindowProperty(Long hObj, Integer nWinNo, Integer nPropertyValue, Integer nPropertyID);

    //Network
    Integer CP5200_Net_Init(Integer dwIP, Integer nIPPort, Integer dwIDCode, Integer nTimeout);

    Integer CP5200_Net_Connect();

    Integer CP5200_Net_Disconnect();

    Integer CP5200_Net_Write(Byte pBuf, Integer nLength);

    Integer CP5200_Net_Read(Byte pBuf, Integer nBufSize);

    Integer CP5200_Net_PlaySelectedPrg(Integer nCardID, Integer pSelected, Integer nSelCnt, Integer nOption);

    Integer CP5200_Net_SendText(Integer nCardID, Integer nWndNo, String pText, Integer crColor, Integer nFontSize, Integer nSpeed, Integer nEffect, Integer nStayTime, Integer nAlignment);

    Integer CP5200_Net_SendText1(Integer nCardID, Integer nWndNo, String pText, Integer crColor, Integer nFontSize, Integer nSpeed, Integer nEffect, Integer nStayTime, Integer nAlignment);

    Integer CP5200_Net_SendPicture(Integer nCardID, Integer nWndNo, Integer nPosX, Integer nPosY, Integer nCx, Integer nCy, Byte pPictureFile, Integer nSpeed, Integer nEffect, Integer nStayTime, Integer nPictRef);

    Integer CP5200_Net_SendStatic(Integer nCardID, Integer nWndNo, String pText, Integer crColor, Integer nFontSize, Integer nAlignment, Integer x, Integer y, Integer cx, Integer cy);

    Integer CP5200_Net_SendClock(Integer nCardID, Integer nWinNo, Integer nStayTime, Integer nCalendar, Integer nFormat, Integer nContent, Integer nFont, Integer nRed, Integer nGreen, Integer nBlue, String pTxt);

    Integer CP5200_Net_SetTime(Integer nCardID, Byte pInfo);

    Integer CP5200_Net_SplitScreen(Integer nCardID, Integer nScrWidth, Integer ScrHeight, Integer nWndCnt, Integer pWndRects);

    Integer CP5200_Net_UploadFile(Integer nCardID, String pSourceFilename, String pTargetFilename);

    Integer CP5200_Net_RestartApp(Byte nCardID);

    Integer CP5200_MakeScreenTestData(Long hObj, Byte pBuffer, Integer nBufSize, Byte pInfoBuffer, Integer nInfLength);

    //RS232/485
    Integer CP5200_RS232_InitEx(String fName, Integer nBaund, Integer nTimeout);

    Integer CP5200_RS232_Open();

    Integer CP5200_RS232_Close();

    Integer CP5200_RS232_WriteEx(Byte pBuf, Integer nLength);

    Integer CP5200_RS232_ReadEx(Byte pBuf, Integer nBufSize);

    Integer CP5200_RS232_PlaySelectedPrg(Integer nCardID, Integer pSelected, Integer nSelCnt, Integer nOption);

    Integer CP5200_RS232_SendText(Integer nCardID, Integer nWndNo, String pText, Integer crColor, Integer nFontSize, Integer nSpeed, Integer nEffect, Integer nStayTime, Integer nAlignment);

    Integer CP5200_RS232_SendText1(Integer nCardID, Integer nWndNo, String pText, String crColor, Integer nFontSize, Integer nSpeed, Integer nEffect, Integer nStayTime, Integer nAlignment);

    Integer CP5200_RS232_SendPicture(Integer nCardID, Integer nWndNo, Integer nPosX, Integer nPosY, Integer nCx, Integer nCy, String pPictureFile, Integer nSpeed, Integer nEffect, Integer nStayTime, Integer nPictRef);

    Integer CP5200_RS232_SendStatic(Integer nCardID, Integer nWndNo, String pText, Integer crColor, Integer nFontSize, Integer nAlignment, Integer x, Integer y, Integer cx, Integer cy);

    Integer CP5200_RS232_SendClock(Integer nCardID, Integer nWinNo, Integer nStayTime, Integer nCalendar, Integer nFormat, Integer nContent, Integer nFont, Integer nRed, Integer nGreen, Integer nBlue, String pTxt);

    Integer CP5200_RS232_SetTime(Integer nCardID, Byte pInfo);

    Integer CP5200_RS232_SplitScreen(Integer nCardID, Integer nScrWidth, Integer ScrHeight, Integer nWndCnt, Integer pWndRects);

    Integer CP5200_RS232_UploadFile(Integer nCardID, String pSourceFilename, String pTargetFilename);

    Integer CP5200_RS232_RestartApp(Byte nCardID);

}

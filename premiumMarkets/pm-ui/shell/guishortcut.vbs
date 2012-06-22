strInstallFolder = WScript.Arguments.Item(0)

Set oWS = WScript.CreateObject("WScript.Shell")  

strDesktop = oWS.SpecialFolders("Desktop")
sLinkFile = strDesktop+"\PremiumMarkets.LNK"

Set oLink = oWS.CreateShortcut(sLinkFile)
   
oLink.TargetPath = strInstallFolder+"\shell\gui.bat"
oLink.Arguments = ""
oLink.Description = "Premium Markets is an automated financial technical analysis system. Please refer to Premium Markets PRICE TREND FORECAST web portal at http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features."
'oLink.HotKey = "ALT+CTRL+F"
oLink.IconLocation = strInstallFolder+"\icons\icon.img, 0"
oLink.WindowStyle = "7"
oLink.WorkingDirectory = strInstallFolder+"\shell"
oLink.Save

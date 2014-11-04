--liquibase formatted sql

--changeset PADMA:27
INSERT INTO COUPLET (
   ORIGINAL_TITLE,
   ENGLISH_TRANSLATION,
   ENGLISH_TRANSLITERATION,
   DESCRIPTION,
   SHOW_ON_LANDING_PAGE,
   THUMBNAIL_URL,
   CATEGORY_ID,
   POET_ID
)
VALUES
('कबीरा खड़ा बाज़ार में','Kabir stands in the market','Kabira khada bazaar mein','कबीरा खड़ा बाज़ार में, लिए लुकाठी हाथ जो घर जारे आपना, चले हमारे साथ',true,'',5,6),
('प्रीत करो तो यूं करो','If you must love','Preet karo to yun karo','प्रीत करो तो यूं करो जैसे लोटा डोर गला फन्सावे आपना, पानी पीवे कोई और',true,'',5,7),
('प्रेम ना बाड़ी उपजे','Love doesn’t grow on trees','Prem na baadi upaje','प्रेम ना बाड़ी उपजे, प्रेम ना हाट बिका  बिना प्रेम का मानवा, बंधिया जमपुर जाए',false,'',5,7),
('बदरा उठा प्रेम का','A cloud of love arose','Badra uthha prem ka','बदरा उठा प्रेम का, हम पर बरसा होए सीली हो गयी आत्मा, हरी भयी बन राए',false,'',5,7),
('दौड़त दौड़त दौड़िया','The mind made you run','Daudat daudat daudiya','दौड़त दौड़त दौड़िया, जहां लग मन की दौड़ दौड़ थके मन स्थिर भया, तो वस्तु ठोर की ठोर',true,'',5,7),
('गुरु गोविन्द दोनों खड़े','Guru and God both are before me','Guru govind donon khade','गुरु गोविन्द दोनों खड़े, कांके लागूं पाए बलिहारी गुरुदेव की, जीने गोविन्द दियो बताए',true,'',5,7),
('कबीरा तेरी झोंपड़ी','Kabir your house is located','Kabira teri jhompadi','कबीरा तेरी झोंपड़ी गल कटियन के पास करेगा सो नर भरेगा, तू क्यों फिरे उदास',true,'',5,6),
('कबीर कुआँ एक है','Kabir says the well is one','Kabir kuan ek hai','कबीर कुआँ एक है, पनिहारी अनेक बर्तन सब में न्यारे न्यारे, पानी सब में एक',false,'',5,6),
('कबीर कबीर क्या कहे','Why chant Kabir, Kabir','Kabir Kabir kya kahe','कबीर कबीर क्या कहे, सोचो आप शरीर पांच इंद्री वश करो, आप ही दास कबीर',true,'',5,7);

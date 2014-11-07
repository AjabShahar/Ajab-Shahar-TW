--liquibase formatted sql

--changeset PADMA:41
UPDATE COUPLET SET
ORIGINAL_TEXT = 'कबीरा खड़ा बाज़ार में, लिए लुकाठी हाथ\nजो घर जारे आपना, चले हमारे साथ',
ENGLISH_TRANSLATION_TEXT = 'Kabir stands in the market, flaming torch in hand\nBurn down your house, then come walk with me',
ENGLISH_TRANSLITERATION_TEXT='Kabira khada bazaar mein, liye lukaathhi haath\nJo ghar jaare aapna, chale hamaare saath'
WHERE ORIGINAL_TITLE='कबीरा खड़ा बाज़ार में';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'प्रीत करो तो यूं करो जैसे लोटा डोर\nगला फन्सावे आपना, पानी पीवे कोई और',
ENGLISH_TRANSLATION_TEXT = 'If you must love, do so like a bucket in a string\nPlunges down to fetch water that another one will drink',
ENGLISH_TRANSLITERATION_TEXT = 'Preet karo to yun karo, jaise lota dor\nGala phansaave aapna, paani piye koi aur'
WHERE ORIGINAL_TITLE = 'प्रीत करो तो यूं करो';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'प्रेम ना बाड़ी उपजे, प्रेम ना हाट बिकाए\nबिना प्रेम का मानवा, बंधिया जमपुर जाए	',
ENGLISH_TRANSLATION_TEXT = 'Love doesn’t grow on trees\nNor is it sold in a market place\nA person who knows no love\nIs locked in Death’s embrace',
ENGLISH_TRANSLITERATION_TEXT = 'Prem na baadi upaje, prem na haat bikaaye\nBina prem ka maanava, banchiya jampur jaaye'
WHERE ORIGINAL_TITLE = 'प्रेम ना बाड़ी उपजे';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'बदरा उठा प्रेम का, हम पर बरसा होए\nसीली हो गयी आत्मा, हरी भयी बन राए',
ENGLISH_TRANSLATION_TEXT = 'A cloud of love arose\nShowered down on me\nThe soul has moistened\nThe forest is full and green',
ENGLISH_TRANSLITERATION_TEXT = 'Badra uthha prem ka, ham par barsa hoye\nSeeli ho gayi aatma, hari bhari ban raaye'
WHERE ORIGINAL_TITLE='बदरा उठा प्रेम का';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'दौड़त दौड़त दौड़िया, जहां लग मन की दौड़\nदौड़ थके मन स्थिर भया, तो वस्तु ठोर की ठोर',
ENGLISH_TRANSLATION_TEXT = 'The mind made you run\nYou ran, as far as the mind could fare\nTired of its flight the mind grew still\nAnd the object was right there',
ENGLISH_TRANSLITERATION_TEXT = 'Daudat daudat daudiya, jahaan lag mann ki daud\nDaud thake mann sthir bhaya, to vastu thhor ki thhor'
WHERE ORIGINAL_TITLE='दौड़त दौड़त दौड़िया';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'गुरु गोविन्द दोनों खड़े, कांके लागूं पाए\nबलिहारी गुरुदेव की, जीने गोविन्द दियो बताए',
ENGLISH_TRANSLATION_TEXT = 'Guru and God, both are before me\nWhich one should I revere?\nI’m surrendered to the Guru\nWho brought me to God’s sphere',
ENGLISH_TRANSLITERATION_TEXT = 'Guru Govind donon khade, kaanke laagun paaye\nBalihaari Guru dev ki, jine Govind diyo bataaye'
WHERE ORIGINAL_TITLE = 'गुरु गोविन्द दोनों खड़े';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'कबीरा तेरी झोंपड़ी गल कटियन के पास\nकरेगा सो नर भरेगा, तू क्यों फिरे उदास',
ENGLISH_TRANSLATION_TEXT = 'Kabir your house is located\nNext to butchers and cut-throats\nAs they sow, so shall they reap\nWhy do you lose sleep over it?',
ENGLISH_TRANSLITERATION_TEXT = 'Kabira teri jhompdi, gal katiyan ke paas\nKarega so bharega, tu kyun bhayo udaas'
WHERE ORIGINAL_TITLE = 'कबीरा तेरी झोंपड़ी';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'कबीर कुआँ एक है, पनिहारी अनेक\nबर्तन सब में न्यारे न्यारे, पानी सब में एक',
ENGLISH_TRANSLATION_TEXT = 'Kabir says the well is one\nWater bearers many\nThe pots may differ in shape in size\nThe water in them is one',
ENGLISH_TRANSLITERATION_TEXT = 'Kabir kuan ek hai, panihaari anek\nBartan sab mein nyaare nyaare, paani sab mein ek'
WHERE ORIGINAL_TITLE = 'कबीर कुआँ एक है';
UPDATE COUPLET SET
ORIGINAL_TEXT = 'कबीर कबीर क्या कहे, सोचो आप शरीर\nपांच इंद्री वश करो, आप ही दास कबीर',
ENGLISH_TRANSLATION_TEXT = 'Why chant Kabir, Kabir?\nLook within your body\nGet a grip on your senses\nAnd become Kabir yourself',
ENGLISH_TRANSLITERATION_TEXT = 'Kabir Kabir kya kahe, socho aap shareer\nPaanch indri vash karo, aap hi Das Kabir'
WHERE ORIGINAL_TITLE = 'कबीर कबीर क्या कहे';

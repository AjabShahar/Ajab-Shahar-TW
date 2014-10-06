var contentService = function ($http) {
  var getLandingPageThumbnails = function () {
    return {
        "details" :[
                    {
                        "category":"Songs",
                        "categoryName":"Song & Reflection",
                        "name":"Practice the art of dying",
                        "poet":"Sharath",
                        "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "singer":"Parvathy Baul",
                        "duration":"09:11",
                    },
                    {
                        "category":"Films",
                        "categoryName":"Film Episode",
                        "context":"Prahlad Tipanya Meets His Guru",
                        "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "description":"in the film KOI SUNTA HAI",
                        "duration":"21 : 00",
                    },
                    {
                        "category":"Reflections",
                        "name":"The Ulatbansi of Kabir",
                        "by":"Linda Hess",
                        "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Songs",
                        "categoryName":"Song & Talk",
                        "name":"My Song My Life",
                        "description":"Satsang with GAVRA DEVI",
                        "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "duration":"35 : 09",
                    },
                    {
                        "category":"Unknown",
                        "categoryName":"School Experiment",
                        "name":"Parrot & The Jungle Fire",
                        "description":"Dance drama by children of HBP School",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Unknown",
                        "categoryName":"ArtWork",
                        "name":"Kahat Kabir",
                        "description":"Paintings by GHULAM SHEIKH",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Words",
                        "categoryName":"WORD INTRO",
                        "name":"Untellable Tale",
                        "description":"An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Unknown",
                        "categoryName":"Story",
                        "name":"10 Images X 100 Words",
                        "description":"Photo Essay by SMRITI CHANCHANI & VIPUL RIKHI",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Songs",
                        "categoryName":"Song",
                        "name":"Everyday I Wish You Well",
                        "description":"sings MUKHTIYAR ALI",
                        "poet":"ULLEH SHAH0",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "duration":"09 : 11",
                    },
                    {
                        "category":"Relections",
                        "categoryName":"Relection",
                        "name":"Reinterpreting Kabir’s life & times",
                        "description":"by PURUSHOTTAM AGRAWAL",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                        "duration":"09 : 11",
                    },
                    {
                        "category":"Gathering",
                        "name":"Bangalore Festival Of Kabir",
                        "description":"Bangalore 2009",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Couplets",
                        "categoryName":"Couplet",
                        "name":"A pot in water, water in a pot...",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Couplets",
                        "categoryName":"Couplet",
                        "name":"A pot in water, water in a pot...",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
                    {
                        "category":"Gathering",
                        "name":"Evening With Madan Gopal Singh",
                        "description":"Bangalore 2011",
                        "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                    },
        ]
    };
  };

  return {
    getLandingPageThumbnails: getLandingPageThumbnails,
  };
};

mainLandingPageApp.factory('contentService', ['$http', contentService]);
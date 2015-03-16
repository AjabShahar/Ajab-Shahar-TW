describe("reflection details controller specs",function(){

   var scope,$location,fakeWindow, httpBackend;

   beforeEach(module('reflectionsAdminApp'));

   beforeEach(inject(function(_$controller_, $rootScope, _$window_, _$location_,_$httpBackend_,reflectionContentService,loginVerifyService,$cookies) {
     scope = $rootScope.$new();
     $location = _$location_;
     httpBackend = _$httpBackend_;
     fakeWindow = { location: { href: '' } };
     $cookies.user= "admin";
       
     _$controller_('reflectionDetailsController', {
     			$scope: scope,
     			$window: fakeWindow,
     			$location: _$location_,
     			reflectionContentService : reflectionContentService,
                loginVerifyService:loginVerifyService
     });

     httpBackend.expectGET('/api/people').respond(200,['people data']);

   }));

   describe("should test save of reflection", function(){
      it("after saving should redirect to admin page",function(){
             httpBackend.expectPOST('/api/reflections').respond(200);

             scope.saveData();
             httpBackend.flush();

             expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
      });

   });


   describe("should test updating of reflection", function(){
      it("after updating should redirect to admin page",function(){

              httpBackend.expectPOST('/api/reflections/edit').respond(200, {'id':1});

              scope.updateData();
              httpBackend.flush();

              expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
      });

   });

});

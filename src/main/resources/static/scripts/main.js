 $(document).ready(function () {

     getQuestionable();

     //Event Listener for the selectAll checkbox:
     $(".selectAll").change(function () {
         var parentTable = $(this).parentsUntil("div");
         if (this.checked) {
             parentTable.find("input[type='checkbox']").prop("checked", true);

         } else {
             parentTable.find("input[type='checkbox']").prop("checked", false);
         }
     });

     $("#tab-bar li").click(function () {
         var tableToShow = $(this).attr("linkto");
         if (!$(this).hasClass("selected")) {
             $(this).addClass("selected");
             $("#tab-bar li").not(this).removeClass("selected");
             var table = $("#" + tableToShow);
             $("div").has("table").addClass("hidden");
             $("#" + tableToShow).removeClass("hidden");
         }
         refreshTable(tableToShow);
     })

     $("input[type='checkbox']").change(function () {
         console.log(this);
         var div = $(this).parentsUntil("body");
         if (this.checked) {
             div.find("button.directive").removeClass("hidden");
             div.find("button.refresh").addClass("hidden");
         } else {
             div.find("button.directive").addClass("hidden");
             div.find("button.refresh").removeClass("hidden");
         }
     });
     
     $(".refresh").click(function() {
         var tableToShow = $(this).parent().attr("id");
         refreshTable(tableToShow);
     })

 });

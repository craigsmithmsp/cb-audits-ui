 $(document).ready(getQuestionable);

 
 //Ajax call for getting all questionable profiles:
 function getQuestionable() {
     $.ajax({
             type: "get",
             url: "http://localhost:11000/ui/audit/summaries"
         })
         .done(function (questionableList) {
             var questionableRow = "";

             var count = questionableList.length;
             $("#questionableTab a").append(" (" + count + ")");

             for (var i = 0; i < questionableList.length; i++) {

                 questionableRow += "<tr>";
                 var currentQuestionable = questionableList[i];

                 var profileName = currentQuestionable['profileName'];
                 questionableRow += ("<td>" + profileName + "</td>");

                 var profileEmail = currentQuestionable['profileEmail'];
                 questionableRow += ("<td>" + profileEmail + "</td>");

                 var siteName = currentQuestionable['siteName'];
                 questionableRow += ("<td>" + siteName + "</td>");

                 var reasons = currentQuestionable['findings'][0]['ruleName'];
                 questionableRow += ("<td>" + reasons + "</td>");

                 questionableRow += ("<td><input type='checkbox' ></td>");

                 questionableRow += "</tr>";
             }
             $("#questionable table").append(questionableRow);
         });
 }

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
     if (!$(this).hasClass("selected")) {
         $(this).addClass("selected");
         $("#tab-bar li").not(this).removeClass("selected");
         
         var tableToShow = $(this).attr("linkto");
         
         var table = $("#" + tableToShow);
         $("div").not(this).has("table").addClass("hidden");
         $("#"+tableToShow).removeClass("hidden");

     }
 })

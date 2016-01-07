<%@ taglib prefix="security"
	   uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bigdata Ready Enterprise</title>
	<!-- Include one of jTable styles. -->

	<link href="../css/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="../css/jtables-bdre.css" rel="stylesheet" type="text/css" />
	<link href="../css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />

	<!-- Include jTable script file. -->
	<script src="../js/jquery.min.js" type="text/javascript"></script>
	<script src="../js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
	<script src="../js/jquery.jtable.js" type="text/javascript"></script>

	<script type="text/javascript">
		    $(document).ready(function () {
	    $('#Container').jtable({
	    title: 'Process Permission List',
		    paging: true,
		    pageSize: 10,
		    sorting: true,
		    actions: {
		    listAction: function (postData, jtParams) {
		    console.log(postData);
			    return $.Deferred(function ($dfd) {
			    $.ajax({
			    url: '/mdrest/processpermission?page=' + jtParams.jtStartIndex + '&size='+jtParams.jtPageSize,
				    type: 'GET',
				    data: postData,
				    dataType: 'json',
				    success: function (data) {
				    $dfd.resolve(data);
				    },
				    error: function () {
				    $dfd.reject();
				    }
			    });
			    });
		    },
	    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">

			    updateAction: function (postData) {
			    console.log(postData);
				    return $.Deferred(function ($dfd) {
				    $.ajax({
				    url: '/mdrest/processpermission',
					    type: 'POST',
					    data: postData,
					    dataType: 'json',
					    success: function (data) {
					    $dfd.resolve(data);
					    },
					    error: function () {
					    $dfd.reject();
					    }
				    });
				    });
			    },
			    deleteAction: function (item) {
			    console.log(item);
				    return $.Deferred(function ($dfd) {
				    $.ajax({
				    url: '/mdrest/processpermission/' + item.batchStateId,
					    type: 'DELETE',
					    data: item,
					    dataType: 'json',
					    success: function (data) {
					    $dfd.resolve(data);
					    },
					    error: function () {
					    $dfd.reject();
					    }
				    });
				    });
			    }</security:authorize>
		    },
		    fields: {
		    processId: {
		    key : true,
                            list: true,
                            create:false,
                            edit: false,
                            title: 'Id'
		    },
		     role: {
                            key : true,
                            list: true,
                            create:false,
                            edit: false,
                            type: 'combobox',
                            options: {'2': 'Administrator', '1': 'Application Developer', '3': 'Readonly User'},
                            title: 'Role'

            },
              owner: {
                            key : true,
                            list: true,
                            create:false,
                            edit: false,
                            title: 'Owner'
            },
              ownerR: {
                            key : true,
                            list: true,
                            edit: false,
                            type: 'combobox',
                            options: { '1': 'Yes', '0': 'No'},
                            defaultValue:'1',

                            title: 'OwnR'
            },
            ownerW: {
                            key : true,
                            list: true,
                            create:false,
                            edit: false,
                            type: 'combobox',
                            options: { '1': 'Yes', '0': 'No'},
                            defaultValue:'1',

                            title: 'OwnW'
            },
            ownerX: {
                            key : true,
                            list: true,
                            edit: false,
                            type: 'combobox',
                            options: { '1': 'Yes', '0': 'No'},
                            title: 'OwnX',
                            defaultValue:'1'
            },
            GroupR: {
                            key : true,
                            list: true,
                            edit: true,
                            type: 'combobox',
                                options: { '1': 'Yes', '0': 'No'},
                             defaultValue: 'false',
                            title: 'GrpR'
            },
            GroupW: {
                            key : true,
                            list: true,
                            edit: true,
                            type: 'combobox',
                                options: { '1': 'Yes', '0': 'No'},
                                defaultValue: '0',
                            title: 'GrpW'
            },
            GroupX: {
                            key : true,
                            list: true,
                            edit: true,
                            type: 'combobox',
                            options: { '1': 'Yes', '0': 'No'},
                            defaultValue: '0',
                            title: 'GrpX'
                        },
            otherR: {
                            key : true,
                            list: true,
                            edit: true,
                            type: 'combobox',
                            options: { '1': 'Yes', '0': 'No'},
                            defaultValue: '0',
                            title: 'OthrR'
            },
            otherW: {
                            key : true,
                            list: true,
                            edit: true,
                            type: 'combobox',
                            options: { '1': 'Yes', '0': 'No'},
                            defaultValue: 'false',
                            title: 'OthrW'
            },
            otherX: {
                            key : true,
                            list: true,
                            edit: true,
                            type: 'combobox',
                            options: { '1': 'Yes', '0': 'No'},
                            defaultValue: '0',
                            title: 'OthrX'
                }
		    }
	    });
		    $('#Container').jtable('load');
	    });
	</script>
    </head>
    <body>

    <section style="width:100%;text-align:center;">
	<div id="Container"></div>
    </section>


</body>
</html>
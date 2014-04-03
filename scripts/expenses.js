var expensesApp = angular.module("expensesApp", ["restangular", "ui.date"]);

expensesApp.controller("ExpensesCtrl", ["$scope", "Restangular", function ($scope, $Restangular) {
	$scope.dateOptions = {
		changeMonth: true,
		changeYear: true,	
		dateFormat: "dd/mm/yy"
	};
	
	function loadExpenses() {
		// Retrieve a list of expenses via REST
		$Restangular.one("expenses").getList().then(function(expenses) {
			$scope.expenses = expenses;
		});
	}
	
	$scope.saveExpense = function() {
		if ($scope.expensesform.$valid) {
			// Post the expense via REST
			$Restangular.one("expenses").post(null, $scope.newExpense).then(function() {
				// Reload new expenses list
				loadExpenses();
			});
		}
	};
	
	$scope.clearExpense = function() {
		$scope.newExpense = {};
	};
	
	// Initialise scope variables
	loadExpenses();
	$scope.clearExpense();
}]);

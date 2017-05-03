<div class="modal {{directive.modal.targetClass}}" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog {{directive.modal.sizeClass}}" role="document">
	  
    <div class="modal-content">

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title" id="mySmallModalLabel" ng-bind="directive.modal.title"></h4>
        </div>

        <div class="modal-body" ng-bind-html="directive.modal.body"></div>

		<div class="modal-footer">
			<button type="button" class="btn {{directive.modal.leftBtn.styleClass}}" data-dismiss="modal" ng-bind="directive.modal.leftBtn.content"></button>
			<button type="button" class="btn {{directive.modal.rightBtn.styleClass}}" data-dismiss="modal" 
      ng-click="directive.modal.rightBtn.clickEvent(directive.modal.rightBtn.id)" ng-bind="directive.modal.rightBtn.content"></button>
		</div>
	</div>

  </div>
</div>
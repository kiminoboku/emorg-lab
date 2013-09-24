﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.1008
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WinFormsClient.WebServices {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://web.service.emorg.kiminoboku.pl/", ConfigurationName="WebServices.ResearchOrderer")]
    public interface ResearchOrderer {
        
        // CODEGEN: Parameter 'return' requires additional schema information that cannot be captured using the parameter mode. The specific attribute is 'System.Xml.Serialization.XmlArrayAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="http://web.service.emorg.kiminoboku.pl/ResearchOrderer/takeOrderRequest", ReplyAction="http://web.service.emorg.kiminoboku.pl/ResearchOrderer/takeOrderResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        WinFormsClient.WebServices.takeOrderResponse takeOrder(WinFormsClient.WebServices.takeOrderRequest request);
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIncludeAttribute(typeof(ManagePeripheralsOperation))]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1015")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://web.service.emorg.kiminoboku.pl/")]
    public abstract partial class AbstractOperation : object, System.ComponentModel.INotifyPropertyChanged {
        
        private OperationType operationTypeField;
        
        private bool operationTypeFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public OperationType operationType {
            get {
                return this.operationTypeField;
            }
            set {
                this.operationTypeField = value;
                this.RaisePropertyChanged("operationType");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool operationTypeSpecified {
            get {
                return this.operationTypeFieldSpecified;
            }
            set {
                this.operationTypeFieldSpecified = value;
                this.RaisePropertyChanged("operationTypeSpecified");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1015")]
    [System.SerializableAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://web.service.emorg.kiminoboku.pl/")]
    public enum OperationType {
        
        /// <remarks/>
        MANAGE_PERIPHERALS,
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1015")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://web.service.emorg.kiminoboku.pl/")]
    public partial class ManagePeripheralsOperation : AbstractOperation {
        
        private PeripheralStateChange mouseStateChangeField;
        
        private PeripheralStateChange keyboardStateChangeField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public PeripheralStateChange mouseStateChange {
            get {
                return this.mouseStateChangeField;
            }
            set {
                this.mouseStateChangeField = value;
                this.RaisePropertyChanged("mouseStateChange");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public PeripheralStateChange keyboardStateChange {
            get {
                return this.keyboardStateChangeField;
            }
            set {
                this.keyboardStateChangeField = value;
                this.RaisePropertyChanged("keyboardStateChange");
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.0.30319.1015")]
    [System.SerializableAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://web.service.emorg.kiminoboku.pl/")]
    public enum PeripheralStateChange {
        
        /// <remarks/>
        TURN_ON,
        
        /// <remarks/>
        TURN_OFF,
        
        /// <remarks/>
        DO_NOTHING,
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="takeOrder", WrapperNamespace="http://web.service.emorg.kiminoboku.pl/", IsWrapped=true)]
    public partial class takeOrderRequest {
        
        public takeOrderRequest() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="takeOrderResponse", WrapperNamespace="http://web.service.emorg.kiminoboku.pl/", IsWrapped=true)]
    public partial class takeOrderResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://web.service.emorg.kiminoboku.pl/", Order=0)]
        [System.Xml.Serialization.XmlArrayAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        [System.Xml.Serialization.XmlArrayItemAttribute("operation", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, IsNullable=false)]
        public AbstractOperation[] @return;
        
        public takeOrderResponse() {
        }
        
        public takeOrderResponse(AbstractOperation[] @return) {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ResearchOrdererChannel : WinFormsClient.WebServices.ResearchOrderer, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class ResearchOrdererClient : System.ServiceModel.ClientBase<WinFormsClient.WebServices.ResearchOrderer>, WinFormsClient.WebServices.ResearchOrderer {
        
        public ResearchOrdererClient() {
        }
        
        public ResearchOrdererClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public ResearchOrdererClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ResearchOrdererClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ResearchOrdererClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        WinFormsClient.WebServices.takeOrderResponse WinFormsClient.WebServices.ResearchOrderer.takeOrder(WinFormsClient.WebServices.takeOrderRequest request) {
            return base.Channel.takeOrder(request);
        }
        
        public AbstractOperation[] takeOrder() {
            WinFormsClient.WebServices.takeOrderRequest inValue = new WinFormsClient.WebServices.takeOrderRequest();
            WinFormsClient.WebServices.takeOrderResponse retVal = ((WinFormsClient.WebServices.ResearchOrderer)(this)).takeOrder(inValue);
            return retVal.@return;
        }
    }
}
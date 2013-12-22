﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:2.0.50727.5472
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// This source code was auto-generated by xsd, Version=2.0.50727.3038.
// 
namespace pl.kiminoboku.emorg {
    using System.Xml.Serialization;
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "2.0.50727.3038")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://kiminoboku.pl/emorg")]
    [System.Xml.Serialization.XmlRootAttribute("research", Namespace="http://kiminoboku.pl/emorg", IsNullable=false)]
    public partial class Research {
        
        private AbstractOperation[] operationField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("operation")]
        public AbstractOperation[] operation {
            get {
                return this.operationField;
            }
            set {
                this.operationField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIncludeAttribute(typeof(ManagePeripheralsOperation))]
    [System.Xml.Serialization.XmlIncludeAttribute(typeof(EmptyOperation))]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "2.0.50727.3038")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://kiminoboku.pl/emorg")]
    public abstract partial class AbstractOperation {
        
        private OperationType operationTypeField;
        
        /// <remarks/>
        public OperationType operationType {
            get {
                return this.operationTypeField;
            }
            set {
                this.operationTypeField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "2.0.50727.3038")]
    [System.SerializableAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://kiminoboku.pl/emorg")]
    public enum OperationType {
        
        /// <remarks/>
        MANAGE_PERIPHERALS,
        
        /// <remarks/>
        EMPTY,
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "2.0.50727.3038")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://kiminoboku.pl/emorg")]
    public partial class ManagePeripheralsOperation : AbstractOperation {
        
        private PeripheralStateChange mouseStateChangeField;
        
        private PeripheralStateChange keyboardStateChangeField;
        
        /// <remarks/>
        public PeripheralStateChange mouseStateChange {
            get {
                return this.mouseStateChangeField;
            }
            set {
                this.mouseStateChangeField = value;
            }
        }
        
        /// <remarks/>
        public PeripheralStateChange keyboardStateChange {
            get {
                return this.keyboardStateChangeField;
            }
            set {
                this.keyboardStateChangeField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "2.0.50727.3038")]
    [System.SerializableAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://kiminoboku.pl/emorg")]
    public enum PeripheralStateChange {
        
        /// <remarks/>
        TURN_ON,
        
        /// <remarks/>
        TURN_OFF,
        
        /// <remarks/>
        DO_NOTHING,
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "2.0.50727.3038")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://kiminoboku.pl/emorg")]
    public partial class EmptyOperation : AbstractOperation {
    }
}

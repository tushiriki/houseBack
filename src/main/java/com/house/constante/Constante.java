package com.house.constante;

public class Constante {

	public Constante() {

	} 
 
	public enum trimestre {
        premiere {
            public String toString() {
                return "first quarter";
            }
        },
        deuxieme {
            public String toString() {
                return "second quarter";
            }
        },
        troisieme {
            public String toString() {
                return "third quarter";
            }
        },
        quatrieme {
            public String toString() {
                return "fourth quarter";
            }},
        cinqieme {
            public String toString() {
                return "fifth quarter";
            }},
        sixieme {
            public String toString() {
                return "sixth quarter";
            }},
        septieme {
            public String toString() {
                return "seventh quarter";
            }},
        huitieme {
            public String toString() {
                return "eighth quarter";
            }
        }
    } 
    
    	public enum roles {
        admin {
            public String toString() {
                return "ADMINISTRATEUR";
            }
        },
        enqeteur {
            public String toString() {
                return "INTERVIEWER";
            }
        },
        autre {
            public String toString() {
                return "VISITOR";
            }
        }}
}
